// Active menu
function activeMenu() {
    const links = document.querySelectorAll("#menu a");
    const currentPath = window.location.pathname;

    links.forEach(link => {
        const path = link.getAttribute("href");
        if (currentPath === path) {
            link.querySelector("span").classList.add("active")
        }
    })
}

activeMenu();

// Toggle theme
const btnThemeToggle = document.getElementById("theme-toggle");
btnThemeToggle.addEventListener("click", () => {
    if (document.body.classList.contains("dark")) {
        localStorage.setItem("theme", "light")
    } else {
        localStorage.setItem("theme", "dark")
    }
    document.body.classList.toggle("dark");
})

function activeTheme() {
    const themeValue = localStorage.getItem("theme");
    if (themeValue) {
        if (themeValue === "dark") {
            document.body.classList.add("dark");
        } else {
            document.body.classList.remove("dark");
        }
    } else {
        document.body.classList.remove("dark");
    }
}

activeTheme();


const searchBlog = document.getElementById('searchInput');
const liSearch = document.querySelectorAll('#searchResults li')
searchBlog.addEventListener("keyup", function () {
    let term = this.value;
    console.log(term)
    liSearch.forEach(li => {
        const tile = li.querySelector("header").innerText;
        if (tile.toLowerCase().includes(term.toLowerCase())) {
            li.style.display = "block";
        } else {
            li.style.display = "none";
        }

    })


})
