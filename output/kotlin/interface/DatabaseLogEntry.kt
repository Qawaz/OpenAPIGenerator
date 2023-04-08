package main

interface DatabaseLogEntry {
	val notes : List<String>
	val log : List<LogEntry>
	val created_at : Int
	val id : String
	val state : String
}