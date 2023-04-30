package api

import (
    "crypto/tls"
    "fmt"
    mux2 "github.com/gorilla/mux"
    "log"
    "net/http"
    "time"
)

type ServerConfig struct {
    UseHttps bool
    CertFile string
    KeyFile  string
    Port     int
}

type ServerHandler struct {
    Router  *mux2.Router
    Server  *http.Server
    Service Service
    Config  *ServerConfig

    Bridge *ServiceRouterBridge
    Routes *Routes
}

func (handler *ServerHandler) SetupRouter() {
    if handler.Service == nil {
        panic("handler.Service must not be nil and must be initialized before router is setup")
    }
    if handler.Router == nil {
        panic("handler.Router must not be nil and must be initialized before router is setup")
    }
    handler.Bridge = &ServiceRouterBridge{Service: handler.Service}
    handler.Routes = CreateRoutes(handler.Bridge)
    for _, route := range *handler.Routes {
        handler.Router.HandleFunc(route.Pattern, route.HandlerFunc).Methods(route.Method)
    }
    if handler.Config.UseHttps {
        handler.Router.Schemes("https")
    }
}

func (handler *ServerHandler) SetupServer() {

    if handler.Router == nil {
        panic("handler.Router must setup before use using SetupRouter")
    }
    if handler.Config.Port == 0 {
        panic("Server configured port cannot be zero")
    }

    var tlsConfig *tls.Config

    if handler.Config.UseHttps {
        tlsConfig = &tls.Config{
            MinVersion:       tls.VersionTLS12,
            CurvePreferences: []tls.CurveID{tls.CurveP521, tls.CurveP384, tls.CurveP256},
            CipherSuites: []uint16{
                tls.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                tls.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                tls.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                tls.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                tls.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                tls.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                tls.TLS_RSA_WITH_AES_128_CBC_SHA,
                tls.TLS_RSA_WITH_AES_256_CBC_SHA,
            },
        }
    } else {
        tlsConfig = nil
    }

    handler.Server = &http.Server{
        Addr:         fmt.Sprintf(":%d", handler.Config.Port),
        WriteTimeout: 5 * time.Second,
        Handler:      handler.Router,
        TLSConfig:    tlsConfig,
    }
}

// RunServer Configure And Runs ServerHandler On App Configured Port
func (handler *ServerHandler) RunServer() {
    if !handler.Config.UseHttps {
        //Running HTTP ServerHandler
        err := handler.Server.ListenAndServe()
        if err != nil {
            log.Fatal("error on ListenAndServer", err)
        }
    } else {
        //Running HTTPS ServerHandler
        err := handler.Server.ListenAndServeTLS(handler.Config.CertFile, handler.Config.KeyFile)
        if err != nil {
            log.Fatal("error on ListenAndServeTLS", err)
        }
    }
}