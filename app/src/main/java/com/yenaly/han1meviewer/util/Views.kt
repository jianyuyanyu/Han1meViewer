package com.yenaly.han1meviewer.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import androidx.annotation.DrawableRes
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yenaly.han1meviewer.FROM_VIDEO_TAG
import com.yenaly.han1meviewer.R
import com.yenaly.han1meviewer.ui.activity.SearchActivity
import com.yenaly.yenaly_libs.utils.copyToClipboard
import com.yenaly.yenaly_libs.utils.showShortToast
import com.yenaly.yenaly_libs.utils.startActivity

/**
 * dynamically create tag chips.
 */
@Deprecated("Use [CollapsibleTags] instead.")
internal fun ChipGroup.createTags(tags: List<String>) {
    for (tag in tags) {
        val chip = LayoutInflater.from(context)
            .inflate(R.layout.item_video_tag_chip, this, false) as Chip
        chip.text = tag
        chip.setOnClickListener {
            (context as? Activity)?.startActivity<SearchActivity>(FROM_VIDEO_TAG to tag)
        }
        chip.setOnLongClickListener {
            tag.copyToClipboard()
            // todo: strings.xml
            showShortToast("「$tag」已複製到剪貼簿")
            return@setOnLongClickListener true
        }
        this.addView(chip)
    }
}

inline fun Context.showAlertDialog(foo: MaterialAlertDialogBuilder.() -> Unit) {
    MaterialAlertDialogBuilder(this).apply {
        foo()
    }.show()
}

fun Button.setDrawableTop(@DrawableRes drawableRes: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(0, drawableRes, 0, 0)
}