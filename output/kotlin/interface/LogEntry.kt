package main

interface ThreadInformation {
	val thread_id : Int
	val is_interrupted : Boolean
	val thread_name : String
	val is_alive : Boolean
	val thread_traces : List<StackTraceElem>
	val thread_state : String
	val is_daemon : Boolean
	val priority : Int
}

interface info_map {

}

interface DeviceState {
	val other_threads : List<ThreadInformation>
	val current_thread : ThreadInformation
	val info_map : Info_map
	val is_connected_to_internet : Boolean
	val time_zone : String
	val storage_information : List<DeviceStorageInformation>
}

interface values {

}

interface LogEntry {
	val device_state : DeviceState
	val time_fired : Int
	val values : Values
	val tag : String
	val stack_trace : String
	val type : String
	val message : String
}