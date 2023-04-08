package main

type values struct {

}

type LogEntry struct {
	time_fired int
	values Values
	tag string
	stack_trace string
	type string
	message string
}