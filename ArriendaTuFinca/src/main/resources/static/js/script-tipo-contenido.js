document.addEventListener("DOMContentLoaded", function() {
    const buttons = document.querySelectorAll(".tipo-btn");
    const contentDiv = document.getElementById("content");

    buttons.forEach(button => {
        button.addEventListener("click", function() {
            const contentType = this.getAttribute("data-content");

            if (contentType === "explorar") {
                contentDiv.innerHTML = "<h2>Explora nuevos destinos</h2><p>Aquí encontrarás los destinos más emocionantes para tu próxima aventura.</p>";
            } else if (contentType === "enamoraron") {
                contentDiv.innerHTML = "<h2>Destinos que te enamoraron</h2><p>Revive los destinos que te han dejado sin aliento y planifica tu próximo viaje.</p>";
            }
        });
    });
});


//Mostrar cartas-------------------
document.addEventListener('DOMContentLoaded', function() {
    const contentDiv = document.getElementById('content');
    const buttons = document.querySelectorAll('.tipo-btn');

    const destinations = {
        explorar: [
            {
                image: 'https://i.imgur.com/oYiTqum.jpg',
                thumb: 'https://i.imgur.com/7D7I6dI.png',
                title: 'Jessica Parker',
                status: '1 hour ago',
                description: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Asperiores, blanditiis?'
            },
            // Agrega más destinos aquí
        ],
        enamoraron: [
            {
                image: 'https://i.imgur.com/2DhmtJ4.jpg',
                thumb: 'https://i.imgur.com/sjLMNDM.png',
                title: 'Kim Cattrall',
                status: '3 hours ago',
                description: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Asperiores, blanditiis?'
            },
            // Agrega más destinos aquí
        ]
    };

    function renderCards(type) {
        const data = destinations[type];
        contentDiv.innerHTML = data.map(destination => `
            <li>
                <a href="" class="card">
                    <img src="${destination.image}" class="card__image" alt="" />
                    <div class="card__overlay">
                        <div class="card__header">
                            <svg class="card__arc" xmlns="http://www.w3.org/2000/svg"><path /></svg>                     
                            <img class="card__thumb" src="${destination.thumb}" alt="" />
                            <div class="card__header-text">
                                <h3 class="card__title">${destination.title}</h3>            
                                <span class="card__status">${destination.status}</span>
                            </div>
                        </div>
                        <p class="card__description">${destination.description}</p>
                    </div>
                </a>      
            </li>
        `).join('');
    }

    buttons.forEach(button => {
        button.addEventListener('click', function() {
            const contentType = this.getAttribute('data-content');
            renderCards(contentType);
        });
    });
});
