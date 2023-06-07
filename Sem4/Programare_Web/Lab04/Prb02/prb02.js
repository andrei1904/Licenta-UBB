function submitForm() {
    let name = $("input[id=name]").css("border", "1px solid black");
    let birthday = $("input[id=birthday]").css("border", "1px solid black");
    let age = $("input[id=age]").css("border", "1px solid black");
    let email = $("input[id=email]").css("border", "1px solid black");

    let valid = 1;
    let errors = "Invalid"

    if (name.val().length < 3) {
        errors += " name,";
        name.css("border", "thick solid #FF0000");
        valid = 0;
    }

    const now = new Date();
    now.setHours(0, 0, 0, 0);
    const birthdate = new Date(birthday.val());
    birthdate.setHours(0, 0, 0, 0);

    if (birthdate > now) {
        errors += " birthdate,";
        birthday.css("border", "thick solid #FF0000");
        valid = 0;
    }

    if (age.val() < 1) {
        errors += " age,";
        age.css("border", "thick solid #FF0000");
        valid = 0;
    }

    if (!/\S+@\S+\.\S+/.test(email.val())) {
        errors += " email,";
        email.css("border", "thick solid #FF0000");
        valid = 0;
    }

    errors = errors.slice(0, -1);
    errors += "!";

    if (valid === 0) {
        alert(errors);
        return false;
    }

    alert("Everything is ok!");
    return true;
}