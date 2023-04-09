package main

type AppInformation struct {
	PackageName string `json:"package_name" bson:"package_name"`
	VersionNumber int `json:"version_number" bson:"version_number"`
	Version string `json:"version" bson:"version"`
}