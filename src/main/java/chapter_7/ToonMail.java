package chapter_7;

import common.Result;

public class ToonMail {
    public static void main(String[] args) {
        Map<String, Toon> toons = new Map<String, Toon>()
                .put("Mickey", new Toon("Mickey", "Mouse", "mickey@disney.com"))
                .put("Minnie", new Toon("Minnie", "Mouse"))
                .put("Donald", new Toon("Donald", "Duck", "donald@disney.com"));
        System.out.println(getName("Mickey").flatMap(toons::get).flatMap(Toon::getEmail));
        System.out.println(getName("Minnie").flatMap(toons::get).flatMap(Toon::getEmail));
        System.out.println(getName("Donald").flatMap(toons::get).flatMap(Toon::getEmail));
    }

    private static Result<String> getName(String key) {
        return Result.success(key);
    }
}
