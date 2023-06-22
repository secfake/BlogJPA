const btnModalImage = document.getElementById("btn-modal-image")
const btnChoseImage = document.getElementById("btn-chose-image")
const inputThumbnailEl = document.getElementById("avatar")
const btnDeleteImage = document.getElementById("btn-delete-image")
const imageContainerEl = document.querySelector(".image-container")

let imageList = []
btnModalImage.addEventListener("click", async () => {
    try {
        const data = await fetch(`/api/v1/users/1/files`)
        const dataJson = await data.json()
        console.log(dataJson)
        ///
        imageList = dataJson;
        renderPagination(imageList)
    } catch (e) {
        console.log(e)
    }
})

function renderImages(imageList) {
    imageContainerEl.innerHTML = "";

    let html = ""
    imageList.forEach(i => {
        html += `<div class="image-item" onclick="choseImage(this)" data-id="${i.id}">
                            <img src="/api/v1/files/${i.id}" alt="">
                    </div>`


    })
    imageContainerEl.innerHTML = html;
}

function renderPagination(imageList) {
    $(`.pagination-container`).pagination({
        dataSource: imageList,
        pageSize: 12,
        callback: function (data, pagination) {
            renderImages(data);
        }
    })
}

inputThumbnailEl.addEventListener("change", (event) => {
    // Lấy ra file
    const file = event.target.files[0]
    console.log(file)

    // Tạo đối tượng formData
    const formData = new FormData();
    formData.append("file", file)

    // Gọi API
    fetch(`/api/v1/files`, {
        method: "POST",
        body: formData
    })
        .then((res) => {
            return res.json()
        })
        .then(res => {
            imageContainerEl.insertAdjacentHTML("beforeend", `<div class="image-item" onclick="choseImage(this)" data-id="${res.id}">
                            <img src="/api/v1/files/${res.id}" alt="">
                    </div>`)
        })
        .catch(err => {
            console.log(err)
        })
})

function choseImage(ele) {
    const imageSelected = document.querySelector(".image-item.selected")
    if (imageSelected) {
        imageSelected.classList.remove("selected")
    }
    ele.classList.add("selected")
    btnChoseImage.disabled = false
    btnDeleteImage.disabled = false
}


btnDeleteImage.addEventListener("click", async () => {
    try {
        const imageSelected = document.querySelector(".image-item.selected")
        const imageId = +imageSelected.dataset.id

        await fetch(`/api/v1/files/${imageId}`, {
            method: "DELETE"
        })
        //xóa mảng ban đầu
        imageList = imageList.filter(i => i.id !== imageId);
        renderImages(imageList)
        btnChoseImage.disabled = true
        btnDeleteImage.disabled = true

    } catch (err) {
        console.log(err)
    }
})


btnChoseImage.addEventListener("click", () => {
    const imageSelected = document.querySelector(".image-item.selected")
    const url = imageSelected.getAttribute("src")


})