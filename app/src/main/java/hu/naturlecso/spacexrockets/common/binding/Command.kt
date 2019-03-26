package hu.naturlecso.spacexrockets.common.binding

interface Command {
    fun execute()
    fun canExecute(): Boolean
}
