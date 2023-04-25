package main

type DeviceInformation struct {
	PlatformType *string `json:"platform_type" bson:"platform_type"`
	OsVersion *string `json:"os_version" bson:"os_version"`
	OsName *string `json:"os_name" bson:"os_name"`
	InfoMap map[string]string `json:"info_map" bson:"info_map"`
}