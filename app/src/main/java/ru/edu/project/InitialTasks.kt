package ru.edu.project

object InitialTasks {
    fun get(): List<Task> = listOf(
        Task("Презентация", false),
        Task("Демонстрация", false),
        Task("Сборка", false),
        Task("README", false),
        Task("Репетиция", false),
    )
}