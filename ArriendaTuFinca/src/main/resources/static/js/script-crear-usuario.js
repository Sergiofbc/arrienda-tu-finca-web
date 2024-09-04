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


/*Validacion de correo en el formulario*/
function validarFormulario() {
    var contrasenia = document.getElementById("contrasenia").value;
    var confirmarContrasenia = document.getElementById("confirmarContrasenia").value;
    var correo = document.querySelector("input[name='correo']").value;
    var arrendador = document.getElementById("arrendador");
    var arrendatario = document.getElementById("arrendatario");
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

    // Validar longitud mínima de la contraseña y al menos un número
    if (!contrasenia.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/)) {
        errorElement.innerText = "La contraseña debe tener al menos 8 caracteres y contener al menos un número.";
        return false;
    }

    // Validar que el correo cumpla con la nomenclatura requerida
    if (!correo.match(/^[\w-\.]+@([\w-]+\.)+[a-z]{2,}$/i)) {
        errorElement.innerText = "El correo no cumple con la nomenclatura requerida.";
        return false;
    }

    // Validar que solo uno de los checkboxes esté seleccionado
    if (!(arrendador.checked || arrendatario.checked) || (arrendador.checked && arrendatario.checked)) {
        errorElement.innerText = "Debes seleccionar un solo rol (Arrendador o Arrendatario).";
        return false;
    }

    errorElement.innerText = ""; // Limpiar mensajes de error
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

