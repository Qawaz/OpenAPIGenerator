package token

import (
	"log"
	"net/http"
	"strings"
)

func ExtractTokenFromAuthorizationHeader(r *http.Request) string {
	bearToken := r.Header.Get("Authorization")
	return extractToken(bearToken)
}

func extractToken(bearerToken string) string {
	//normally Authorization the_token_xxx
	strArr := strings.Split(bearerToken, " ")
	if len(strArr) == 2 {
		return strArr[1]
	}
	log.Printf("error bearer token has been splitted to an invalid size %d", len(strArr))
	return ""
}
