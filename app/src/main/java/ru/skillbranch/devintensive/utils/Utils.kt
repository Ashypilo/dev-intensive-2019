package ru.skillbranch.devintensive.utils

import android.icu.text.Transliterator

object Utils {
    fun parseFullName(fullName: String?):Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        if (firstName?.length == 0)
            return null to null
        else if (lastName?.length == 0)
            return firstName to null
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String? {
        val cyril = charArrayOf(' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы','ь','э', 'ю','я','А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z')
        val latin = arrayOf(" ","a","b","v","g","d","e","e","zh","z","i","i","k","l","m","n","o","p","r","s","t","u","f","h","c","ch","sh","sch", "","i", "","e","ju","ya","A","B","V","G","D","E","E","Zh","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F","H","C","Ch","Sh","Sch", "","I", "","E","Ju","Ja","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")

        var result:String? = ""
        val lineChar = payload.toCharArray()
        println(divider.toCharArray()[0])
        for (i in 0..payload.length - 1){
            for (j in 0..cyril.size - 1) {
//                println(cyril[j])
                if (lineChar[i].equals(cyril[j])){
                    if (j == 0)
                        result += divider
                    else
                        result += latin.get(j)
                }
            }
        }

        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName?.length != 0 && firstName != null && lastName?.length != 0 && lastName != null)
            return firstName?.toUpperCase()?.get(0).toString() + lastName?.toUpperCase()?.get(0).toString()
        else if (firstName?.length != 0 && firstName != null && firstName?.get(0).toString() != " ")
            return firstName?.toUpperCase()?.get(0).toString()
        return null
    }
}