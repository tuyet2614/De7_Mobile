package com.example.englishvocabulary

import com.google.gson.Gson

data class allData(
    var listVocab: ArrayList<Vocab>
) {
    public fun addVocab(vocab: Vocab) {

        listVocab.add(vocab)
    }
    public fun toJsonString() : String {
        var gson = Gson()
        return  gson.toJson(this)
    }
}
