package id.neotica.rickpository

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform