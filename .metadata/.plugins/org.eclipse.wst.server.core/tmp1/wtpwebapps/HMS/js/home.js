function scrollToSection(id) {
    const section = document.getElementById(id);
    if (!section) return;

    const yOffset = -70;
    const y = section.getBoundingClientRect().top + window.pageYOffset + yOffset;

    window.scrollTo({
        top: y,
        behavior: "smooth"
    });
}
