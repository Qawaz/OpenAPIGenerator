package main

type AppInformation struct {
	Package_name string `json:"package_name" bson:"package_name"`
	Version_number int `json:"version_number" bson:"version_number"`
	Version string `json:"version" bson:"version"`
}