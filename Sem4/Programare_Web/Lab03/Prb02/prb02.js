function submitForm() {
    document.forms["form"]["name"].style.border = "1px solid black";
    document.forms["form"]["birthday"].style.border = "1px solid black"
    document.forms["form"]["age"].style.border = "1px solid black"
    document.forms["form"]["email"].style.border = "1px solid black"

    let name = document.forms["form"]["name"].value;
    let birthday = document.forms["form"]["birthday"].value;
    let age = document.forms["form"]["age"].value;
    let email = document.forms["form"]["email"].value;
    let valid = 1;

    let errors = "Invalid"

    if (name.length < 3) {
        errors += " name,";
        document.forms["form"]["name"].style.border = "thick solid #FF0000"
        valid = 0;
    }

    const now = new Date();
    now.setHours(0, 0, 0, 0);
    const birthdate = new Date(birthday);
    birthdate.setHours(0, 0, 0, 0);

    if (birthdate > now) {
        errors += " birthdate,";
        document.forms["form"]["birthday"].style.border = "thick solid #FF0000"
        valid = 0;
    }

    if (age < 1) {
        errors += " age,";
        document.forms["form"]["age"].style.border = "thick solid #FF0000"
        valid = 0;
    }

    if (!/\S+@\S+\.\S+/.test(email)) {
        errors += " email,";
        document.forms["form"]["email"].style.border = "thick solid #FF0000"
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