/**
 * header js 
 */


document.addEventListener("DOMContentLoaded", () => {
    const header = document.querySelector("header");

    setTimeout(() => {
        header.classList.add("show");
    }, 100);

    let lastScrollY = window.scrollY;

    window.addEventListener("scroll", () => {
        const currentScrollY = window.scrollY;

        if (currentScrollY > lastScrollY && currentScrollY > 10) {
            header.classList.add("hide");
            header.classList.remove("active");
        } else {
            header.classList.remove("hide");
            header.classList.add("active");
        }

        lastScrollY = currentScrollY;
    });
});