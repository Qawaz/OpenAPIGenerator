package `interface`

interface StackTraceElem {
	val isNativeMethod: Boolean
	val methodName: String
	val fileName: String
	val lineNumber: Int
	val className: String
}