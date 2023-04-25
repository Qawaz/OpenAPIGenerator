type Success struct {
	Success bool `json:"success" bson:"success"`
}

type Error struct {
	Type string `json:"type" bson:"type"`
	Message string `json:"message" bson:"message"`
}

type AppInformation struct {
	PackageName string `json:"package_name" bson:"package_name"`
	VersionNumber int `json:"version_number" bson:"version_number"`
	Version string `json:"version" bson:"version"`
}

type UserInformation struct {
	UserEmail string `json:"user_email" bson:"user_email"`
	UserId string `json:"user_id" bson:"user_id"`
	UserName string `json:"user_name" bson:"user_name"`
}

type DeviceInformation struct {
	PlatformType string `json:"platform_type" bson:"platform_type"`
	OsVersion *string `json:"os_version" bson:"os_version"`
	OsName string `json:"os_name" bson:"os_name"`
	InfoMap map[string]string `json:"info_map" bson:"info_map"`
}

type DisplayInformation struct {
	Orientation string `json:"orientation" bson:"orientation"`
	Top int `json:"top" bson:"top"`
	Left int `json:"left" bson:"left"`
	Bottom int `json:"bottom" bson:"bottom"`
	Right int `json:"right" bson:"right"`
	DisplayName string `json:"display_name" bson:"display_name"`
}

type DeviceStorageInformation struct {
	StorageName string `json:"storage_name" bson:"storage_name"`
	TotalSpace int `json:"total_space" bson:"total_space"`
	FreeSpace int `json:"free_space" bson:"free_space"`
}

type StackTraceElem struct {
	IsNativeMethod bool `json:"is_native_method" bson:"is_native_method"`
	MethodName string `json:"method_name" bson:"method_name"`
	FileName *string `json:"file_name" bson:"file_name"`
	LineNumber int `json:"line_number" bson:"line_number"`
	ClassName string `json:"class_name" bson:"class_name"`
}

type ThreadInformation struct {
	ThreadId int `json:"thread_id" bson:"thread_id"`
	IsInterrupted bool `json:"is_interrupted" bson:"is_interrupted"`
	ThreadName string `json:"thread_name" bson:"thread_name"`
	IsAlive bool `json:"is_alive" bson:"is_alive"`
	ThreadTraces []StackTraceElem `json:"thread_traces" bson:"thread_traces"`
	ThreadState string `json:"thread_state" bson:"thread_state"`
	IsDaemon bool `json:"is_daemon" bson:"is_daemon"`
	Priority int `json:"priority" bson:"priority"`
}

type DeviceState struct {
	OtherThreads []ThreadInformation `json:"other_threads" bson:"other_threads"`
	CurrentThread ThreadInformation `json:"current_thread" bson:"current_thread"`
	InfoMap map[string]string `json:"info_map" bson:"info_map"`
	IsConnectedToInternet *bool `json:"is_connected_to_internet" bson:"is_connected_to_internet"`
	TimeZone *string `json:"time_zone" bson:"time_zone"`
	StorageInformation []DeviceStorageInformation `json:"storage_information" bson:"storage_information"`
}

type LogEntry struct {
	DeviceState DeviceState `json:"device_state" bson:"device_state"`
	TimeFired int `json:"time_fired" bson:"time_fired"`
	Values map[string]string `json:"values" bson:"values"`
	Tag string `json:"tag" bson:"tag"`
	StackTrace string `json:"stack_trace" bson:"stack_trace"`
	Type string `json:"type" bson:"type"`
	Message string `json:"message" bson:"message"`
}

type DatabaseLogEntry struct {
	Notes []string `json:"notes" bson:"notes"`
	Log []LogEntry `json:"log" bson:"log"`
	CreatedAt *int `json:"created_at" bson:"created_at"`
	Id *string `json:"id" bson:"id"`
	State string `json:"state" bson:"state"`
	Device DeviceInformation `json:"device" bson:"device"`
	AppInformation AppInformation `json:"app_information" bson:"app_information"`
	UserInformation UserInformation `json:"user_information" bson:"user_information"`
}