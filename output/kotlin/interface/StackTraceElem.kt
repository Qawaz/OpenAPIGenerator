package main

interface StackTraceElem {
	val is_native_method : Boolean
	val method_name : String
	val file_name : String
	val line_number : Int
	val class_name : String
}