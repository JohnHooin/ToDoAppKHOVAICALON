package com.loc.todoapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.todoapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Duy đẹp trai vcl nuôn, mà sắp rớt môn cmnr :))",
        description = "Một hai ba con dê hai ba. Oxi hóa khử là quá trình oxi hóa khử.",
        image = R.drawable.img_1
    ),
    Page(
        title = "Năm sau em ra trường lương tháng nghìn đô",
        description = "Học tập phấn đấu làm theo tấm gương bác Hồ Chí Minh.",
        image = R.drawable.img_2
    ),
    Page(
        title = "Thề môn mobile này khóc éo chịu được nuôn",
        description = "Làm ơn mau hết môn để con còn thở thứ khác ngoài thở máy.",
        image = R.drawable.img_3
    )
)