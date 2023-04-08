package main

type info_map struct {

}

type DeviceInformation struct {
	platform_type string
	os_version string
	os_name string
	info_map Info_map
}

type AppInformation struct {
	package_name string
	version_number int
	version string
}

type UserInformation struct {
	user_email string
	user_id string
	user_name string
}

type DatabaseLogEntry struct {
	notes []string
	log []LogEntry
	created_at int
	id string
	state string
	device DeviceInformation
	app_information AppInformation
	user_information UserInformation
}