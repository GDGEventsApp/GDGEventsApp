package com.gdgevents.gdgeventsapp.features.event.domain

import java.util.UUID

data class Event(
    val id: String,
    val imageUrl: String,
    val description: String,
    val title: String,
    val location: String,
    val date: String,
)

val DummyEventList = List(5) {
    Event(
        id = UUID.randomUUID().toString(),
        imageUrl = "https://s3-alpha-sig.figma.com/img/1579/c033/aa0672409d80de27ed621ed3480657c0?Expires=1736726400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=fMC6VklVxHgGLZWZjbk7XGJiNnYjQNdAahKgwiIQBc9bDjSCRrNECOLj7DODAVlegaS~cRxaLZXDOUN1H6zEHC3qpt24d~dPPZ~8kYDQCJ3YiylMzj76st~bPyw065G46MtreTdWo-hW4WmI9xH1uLXE1Zl~aw9nYgibPctcKxczqtpN0MlzSToSKaf4tpASwSK~9ISpHgS7pt8EGdpeMTyZPG1P2Fz3PR6SF6paH2JFsKlY6uNLIxwnbnchMmFqI8NUYieL3j5ZfxJWz7oUVAovOXBYyy2NEA3PPzFq4WocQ43zb3wxsTf12v9qhBFrP3pTa1B6gO1YDtj3U8P-rQ__",
        description = "Artificial intelligence (AI) is technology that enables computers and machines to simulate human learning, comprehension, problem solving, decision making, creativity and autonomy. Applications and devices equipped with AI can see and identify objects. They can understand and respond to human language.",
        title = "Gemma 2 Ai Challenge",
        location = "Cairo",
        date = "28 Nov 2024 - 1 Dec 2024"
    )
}
val DummyFeaturedEventList = List(3) {
    Event(
        id = UUID.randomUUID().toString(),
        imageUrl = "https://s3-alpha-sig.figma.com/img/34e6/fe20/c1480f7d7e8cf7356dfee7f37df6cf2a?Expires=1736726400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=f6A7Fvd6Y-YBY7LiuSdn7S1p5OSHg59rx~sPzLIP8P0UfOFMHZ15bqTDd5Mimjom0kkWD0qbvbN01xZaj35J3Qgi8bAeSbOaeZupXwMibKYncXBa6z-~9HV60vwl-0IOkJ8DWJB74Bp1kMUvkrovKlMNGeNAabHRRBieAjzIjJiZDJiN01Px9G1Of-U0-mH3j36k1~EWWlKG~6nWp~SYofxBc~ok5RQhRDzE3~nq9PYJ9das6uFGHcBYESthZLhnaVihxLzK3Dj35fxtbhC79xpIaxUsin8E3WCMn-X1~4m7eJUc4Q3BEAucMtSnnvVZ98-tO9TkkpFJnjh54HxF8g__",
        description = "",
        title = "DevFest 2024",
        location = "Cairo",
        date = "12 Dec 2024"
    )
}