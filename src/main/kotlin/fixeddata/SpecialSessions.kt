package fixeddata

import entity.Room
import entity.Session
import network.parsing.parseISO8601Date

class SpecialSessions {
    companion object {
        fun getSessions(): List<Session.SpecialSession> {
            var index = 0
            val specialSessionRoom = Room(513, "Hall")
            return listOf(
                    Session.SpecialSession(
                            "100000" + index++,
                            1,
                            parseISO8601Date("2018-02-08T10:00:00"),
                            parseISO8601Date("2018-02-08T10:20:00"),
                            "Welcome talk",
                            specialSessionRoom
                    ),
                    Session.SpecialSession(
                            "100000" + index++,
                            1,
                            parseISO8601Date("2018-02-08T11:50:00"),
                            parseISO8601Date("2018-02-08T12:50:00"),
                            "Lunch",
                            null
                    ),
                    Session.SpecialSession(
                            "100000" + index++,
                            1,
                            parseISO8601Date("2018-02-08T17:40:00"),
                            parseISO8601Date("2018-02-08T19:40:00"),
                            "Party",
                            specialSessionRoom
                    ),

                    Session.SpecialSession(
                            "100000" + index,
                            2,
                            parseISO8601Date("2018-02-09T11:50:00"),
                            parseISO8601Date("2018-02-09T12:50:00"),
                            "Lunch",
                            null
                    ))
        }
    }
}