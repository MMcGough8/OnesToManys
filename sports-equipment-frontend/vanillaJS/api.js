const API_BASE_URL = 'http://localhost:8080/api';

async function getAllBrands() {
    const response = await fetch(`${API_BASE_URL}/brands`);
    return await response.json();
}

async function getBrand(id) {
    const response = await fetch(`${API_BASE_URL}/brands/${id}`);
    return await response.json();
}

async function getBrandProducts(brandId) {
    const response = await fetch(`${API_BASE_URL}/products/brand/${brandId}`);
    return await response.json();
}

async function createBrand(brandData) {
    const response = await fetch(`${API_BASE_URL}/brands`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(brandData)
    });
    return await response.json();
}

async function updateBrand(id, brandData) {
    const response = await fetch(`${API_BASE_URL}/brands/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(brandData)
    });
    return await response.json();
}

async function deleteBrand(id) {
    const response = await fetch(`${API_BASE_URL}/brands/${id}`, {
        method: 'DELETE'
    });
    return response;
}

async function getAllProducts() {
    const response = await fetch(`${API_BASE_URL}/products`);
    return await response.json();
}

async function getProduct(id) {
    const response = await fetch(`${API_BASE_URL}/products/${id}`);
    return await response.json();
}

async function createProduct(productData) {
    const response = await fetch(`${API_BASE_URL}/products`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(productData)
    });
    return await response.json();
}

async function updateProduct(id, productData) {
    const response = await fetch(`${API_BASE_URL}/products/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(productData)
    });
    return await response.json();
}

async function deleteProduct(id) {
    const response = await fetch(`${API_BASE_URL}/products/${id}`, {
        method: 'DELETE'
    });
    return response;
}