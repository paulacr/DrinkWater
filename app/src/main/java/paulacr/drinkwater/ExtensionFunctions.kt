package paulacr.drinkwater

fun String.getFormattedNumber() =
    this.replace(" ml", "")
        .replace(".", "")
