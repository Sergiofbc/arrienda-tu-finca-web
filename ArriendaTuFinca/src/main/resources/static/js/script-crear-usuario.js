// Archivo: script-crear-usuario.js

function validarFormulario() {
    var contrasenia = document.getElementById("contrasenia").value;
    var confirmarContrasenia = document.getElementById("confirmarContrasenia").value;
    var errorElement = document.getElementById("error-msg");

    // Validar que las contraseñas coincidan
    if (contrasenia !== confirmarContrasenia) {
        errorElement.innerText = "Las contraseñas no coinciden.";
        return false;
    }

    // Validar que la contraseña no contenga la letra "ñ"
    if (contrasenia.includes("ñ")) {
        errorElement.innerText = "La contraseña no puede contener la letra ñ.";
        return false;
    }

    // Limpiar el mensaje de error si todo está correcto
    errorElement.innerText = "";
    return true;
}


// Checkbox desselction logic

function validarFormulario() {
    var contrasenia = document.getElementById("contrasenia").value;
    var confirmarContrasenia = document.getElementById("confirmarContrasenia").value;
    var errorElement = document.getElementById("error-msg");

    // Validar que las contraseñas coincidan
    if (contrasenia !== confirmarContrasenia) {
        errorElement.innerText = "Las contraseñas no coinciden.";
        return false;
    }

    // Validar que la contraseña no contenga la letra "ñ"
    if (contrasenia.includes("ñ")) {
        errorElement.innerText = "La contraseña no puede contener la letra ñ.";
        return false;
    }

    // Limpiar el mensaje de error si todo está correcto
    errorElement.innerText = "";
    return true;
}

// Lógica para deseleccionar el otro checkbox
document.getElementById("arrendador").addEventListener("change", function() {
    if (this.checked) {
        document.getElementById("arrendatario").checked = false;
    }
});

document.getElementById("arrendatario").addEventListener("change", function() {
    if (this.checked) {
        document.getElementById("arrendador").checked = false;
    }
});
