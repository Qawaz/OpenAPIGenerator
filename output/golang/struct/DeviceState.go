package main

type ThreadInformation struct {
	thread_id int
	is_interrupted bool
	thread_name string
	is_alive bool
	thread_traces []StackTraceElem
	thread_state string
	is_daemon bool
	priority int
}

type info_map struct {

}

type DeviceState struct {
	other_threads []ThreadInformation
	current_thread ThreadInformation
	info_map Info_map
	is_connected_to_internet bool
	time_zone string
	storage_information []DeviceStorageInformation
}