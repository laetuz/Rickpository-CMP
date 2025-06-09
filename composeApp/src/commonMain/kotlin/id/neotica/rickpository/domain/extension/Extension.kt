package id.neotica.rickpository.domain.extension

fun String?.extractPageNumberAfterEquals(): Int? {
    if (this.isNullOrEmpty()) {
        return null
    }
    // Regex to find one or more digits (\d+) immediately following an equals sign (=)
    // at the very end of the string ($). The digits are captured in a group.
    val regex = "=(\\d+)$".toRegex()
    val matchResult = regex.find(this)

    // groupValues[0] is the full match (e.g., "=3")
    // groupValues[1] is the first captured group (e.g., "3")
    return matchResult?.groupValues?.get(1)?.toIntOrNull()
}