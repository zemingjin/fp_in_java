package chapter_7;

import common.Result;

public class ToonMail {
    public static void main(String[] args) {
        Map<String, Toon> toons = new Map<String, Toon>()
                .put("Mickey", new Toon("Mickey", "Mouse", "mickey@disney.com"))
                .put("Minnie", new Toon("Minnie", "Mouse"))
                .put("Donald", new Toon("Donald", "Duck", "donald@disney.com"));
        Result<String> result = getName().flatMap(toons::get).flatMap(Toon::getEmail);
        System.out.println(result);
    }

    public static Result<String> getName() {
        return Result.success("Mickey");
    }
}
