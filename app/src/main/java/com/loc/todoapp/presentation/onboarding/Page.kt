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
        title = "To Do List App 2024",
        description = "Sản phẩm chống cháy bài giữa kỳ của tụi em",
        image = R.drawable.img_1
    ),
    Page(
        title = "Bài còn nhiều sai xót về UI/UX",
        description = "Nhóm có không có bông hồng làm đạo diễn nghệ thuật.",
        image = R.drawable.img_2
    ),
    Page(
        title = "Tận dụng vào để học MVVM",
        description = "Các khái niệm cơ bản để phát triển ứng dụng Android",
        image = R.drawable.img_3
    )
)