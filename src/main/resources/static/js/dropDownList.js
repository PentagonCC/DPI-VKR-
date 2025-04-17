document.addEventListener("DOMContentLoaded", function () {
    const catalogMenu = document.querySelector(".catalog-menu");
    const dropdown = document.getElementById("categoryDropdown");
  
    catalogMenu.addEventListener("click", function (event) {
      dropdown.classList.toggle("show");
      event.stopPropagation();
    });
  
    document.addEventListener("click", function () {
      dropdown.classList.remove("show");
    });
  });
  