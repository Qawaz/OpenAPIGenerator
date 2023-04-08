package main

type DatabaseLogEntry struct {
	notes []string
	log []LogEntry
	created_at int
	id string
	state string
}