import java.io.File
import java.lang.NullPointerException
import java.util.*

data class PassportData(var byr: String = "",
                        var iyr: String = "",
                        var eyr: String = "",
                        var hgt: String = "",
                        var hcl: String = "",
                        var ecl: String = "",
                        var pid: String = "",
                        var cid: String = "") {
    fun isValid(): Boolean {
        try {
            if (byr.toInt() < 1920 || byr.toInt() > 2002) {
                return false
            }

            if (iyr.toInt() < 2010 || iyr.toInt() > 2020) {
                return false
            }

            if (eyr.toInt() < 2020 || eyr.toInt() > 2030) {
                return false
            }

            val heightMatch = Regex("(\\d+)(cm|in)").find(hgt)
            if (heightMatch != null) {
                val (height, unit) =  heightMatch.destructured

                if (unit == "cm") {
                    if (height.toInt() < 150 || height.toInt() > 193) {
                        return false
                    }
                } else {
                    if (height.toInt() < 59 || height.toInt() > 76) {
                        return false
                    }
                }
            } else {
                return false
            }

            if (!Regex("#[a-f0-9]{6}").matches(hcl)) {
                return false
            }

            if (!Regex("amb|blu|brn|gry|grn|hzl|oth").matches(ecl)) {
                return false
            }

            if (!Regex("[\\d]{9}").matches(pid)) {
                return false
            }

        } catch (e: NumberFormatException) {
            return false
        } catch (e: NullPointerException) {
            return false
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return true
    }

    fun reset() {
        byr = ""
        iyr = ""
        eyr = ""
        hgt = ""
        hcl = ""
        ecl = ""
        pid = ""
        cid = ""
    }

}



fun main() {
    var passportData = PassportData()

    var validPassports = 0

    File("input").readLines().forEach { line ->
        if (line == "") {
            if (passportData.isValid()) {
                validPassports += 1
            }
            passportData.reset()
        } else {
            line.split(" ").forEach { token ->
                getTokenValue(token, "byr").ifPresent({v -> passportData.byr = v})
                getTokenValue(token, "iyr").ifPresent({v -> passportData.iyr = v})
                getTokenValue(token, "eyr").ifPresent({v -> passportData.eyr = v})
                getTokenValue(token, "hgt").ifPresent({v -> passportData.hgt = v})
                getTokenValue(token, "hcl").ifPresent({v -> passportData.hcl = v})
                getTokenValue(token, "ecl").ifPresent({v -> passportData.ecl = v})
                getTokenValue(token, "pid").ifPresent({v -> passportData.pid = v})
                getTokenValue(token, "cid").ifPresent({v -> passportData.cid = v})
            }
        }
    }

    if (passportData.isValid()) {
        validPassports += 1
    }
    passportData.reset()

    println(validPassports)
}

fun getTokenValue(line: String, type: String): Optional<String> {
    if (!line.contains("${type}:")) {
        return Optional.empty()
    } else {
        val regexStr = "${type}:([^ ]+)"
        return Optional.ofNullable(Regex(regexStr).find(line)).map { m -> m.destructured.component1() }
    }
}
