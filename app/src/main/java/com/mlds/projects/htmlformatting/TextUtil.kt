package com.mlds.projects.htmlformatting


import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

fun String.toHtml(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
} else {
    @Suppress("DEPRECATION")
    Html.fromHtml(this)
}

fun String.styleText(start: String, end: String): SpannableString = this.toHtml().localizeTag(this, start, end)

// Localiza todas as tags <b> no texto HTML e adiciona seus índices à lista
fun Spanned.localizeTag(htmlString: String, start: String, end: String): SpannableString {
    val boldSpan = StyleSpan(Typeface.BOLD)
    val foregroundColorSpan = ForegroundColorSpan(Color.RED)
    val startTags = ArrayList<Int>()
    var index = -1

    while (true) {
        index = htmlString.indexOf(start, index + 1)
        if (index == -1) {
            break
        }
        startTags.add(index)
    }

    // Percorre a lista de índices das tags <b> e cria spans de texto em negrito
    val spannableString = SpannableString(this)
    for (startTag in startTags) {
        val endTag = htmlString.indexOf(end, startTag)
        if (endTag != -1) {
            spannableString.setSpan(boldSpan, startTag, endTag, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(foregroundColorSpan, startTag, endTag, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    return spannableString
}
