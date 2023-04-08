package main

type DeviceInformation struct {
	Platform_type string `json:"platform_type" bson:"platform_type"`
	Os_version string `json:"os_version" bson:"os_version"`
	Os_name string `json:"os_name" bson:"os_name"`
	Info_map map[string]string `json:"info_map" bson:"info_map"`
}