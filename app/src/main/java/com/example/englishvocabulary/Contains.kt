package com.example.englishvocabulary

object Contains {
    fun getQuestions(): ArrayList<question> {
        val questionsList = ArrayList<question>()
        var question1 = question(
            1,
            "I go there ____?",
            "every day",
            "every days",
            "everyday",
            "everydays",
            1
        )
        questionsList.add(question1)

        var question2 = question(
            2,
            "When we went back to the bookstore, the bookseller _______ the book we wanted?",
            "sold",
            "had sold",
            "sells",
            "has sold",
            2
        )
        questionsList.add(question2)

        var question3 = question(
            3,
            "By the end of last summer, the farmers _______ all the crop?",
            "harvested",
            "are harvested ",
            "harvest",
            "had harvested",
            4
        )
        questionsList.add(question3)

        var question4 = question(
            4,
            "The director _______ for the meeting by the time I got to his office?",
            "left",
            "had left",
            "leaves",
            "will leave",
            2
        )
        questionsList.add(question4)

        var question5 = question(
            5,
            "Susan _______ her family after she had taken the university entrance examination?",
            "phoned",
            "had phoned",
            "phones",
            "is phoning",
            1
        )
        questionsList.add(question5)
        return questionsList
    }
}