// Get the container element
const brandsContainer = document.getElementById('brands-container');

// Get the buttons
const addBrandBtn = document.getElementById('add-brand-btn');
const addProductBtn = document.getElementById('add-product-btn');

// Load all brands with their products
async function loadBrandsAndProducts() {
    try {
        // Get all brands
        const brands = await getAllBrands();
        
        // Display them
        displayBrands(brands);
    } catch (error) {
        console.error('Error loading data:', error);
        alert('Error... Make sure your API is running!');
    }
}

// Display brands and their products
async function displayBrands(brands) {
    // Clear the container
    brandsContainer.innerHTML = '';
    
    // If no brands, show message
    if (brands.length === 0) {
        brandsContainer.innerHTML = '<p>No brands yet. Add one!</p>';
        return;
    }

    for (const brand of brands) {
        // Get products for this brand
        const products = await getBrandProducts(brand.id);
        
        // Create brand section
        const brandSection = createBrandSection(brand, products);
        brandsContainer.appendChild(brandSection);
    }
}

// Create HTML for a brand section
function createBrandSection(brand, products) {
    const section = document.createElement('div');
    section.className = 'brand-section';
    
    // Brand header
    const header = `
        <div class="brand-header">
            <h3>${brand.name}</h3>
            <p>Founded: ${brand.foundingYear || 'N/A'}</p>
            <p>Website: ${brand.website || 'N/A'}</p>
            <p>${brand.description || ''}</p>
            <button onclick="handleEditBrand(${brand.id})">Edit</button>
            <button onclick="handleDeleteBrand(${brand.id})">Delete</button>
        </div>
    `;
    
    // Products section
    let productsHTML = '<div class="products-section"><h4>Products:</h4>';
    
    if (products.length === 0) {
        productsHTML += '<p>No products yet.</p>';
    } else {
        products.forEach(product => {
            productsHTML += `
                <div class="product-card">
                    <h5>${product.name}</h5>
                    <p>SKU: ${product.sku}</p>
                    <p>Price: $${product.price}</p>
                    <p>Category: ${product.sportCategory}</p>
                    <p>Type: ${product.productType}</p>
                    <p>Stock: ${product.stockQuantity}</p>
                    <button onclick="handleEditProduct(${product.id})">Edit</button>
                    <button onclick="handleDeleteProduct(${product.id})">Delete</button>
                </div>
            `;
        });
    }
    productsHTML += '</div>';
    
    section.innerHTML = header + productsHTML;
    return section;
}

// Add brand
async function handleAddBrand() {
    const name = prompt('Brand name:');
    if (!name) return;
    
    const foundingYear = prompt('Founding year (optional):');
    const website = prompt('Website (optional):');
    const description = prompt('Description (optional):');
    
    try {
        await createBrand({
            name: name,
            foundingYear: foundingYear ? parseInt(foundingYear) : null,
            website: website || null,
            description: description || null,
            isActive: true
        });
        
        alert('Brand created!');
        loadBrandsAndProducts();
    } catch (error) {
        console.error('Error creating brand:', error);
        alert('Error creating brand!');
    }
}

// Edit brand
async function handleEditBrand(id) {
    try {
        const brand = await getBrand(id);
        
        const name = prompt('Brand name:', brand.name);
        if (!name) return;
        
        const foundingYear = prompt('Founding year:', brand.foundingYear);
        const website = prompt('Website:', brand.website);
        const description = prompt('Description:', brand.description);
        
        await updateBrand(id, {
            name: name,
            foundingYear: foundingYear ? parseInt(foundingYear) : null,
            website: website || null,
            description: description || null,
            isActive: brand.isActive
        });
        
        alert('Brand updated!');
        loadBrandsAndProducts();
    } catch (error) {
        console.error('Error updating brand:', error);
        alert('Error updating brand!');
    }
}

// Delete brand
async function handleDeleteBrand(id) {
    const confirmed = confirm('Delete this brand? This will also delete all its products!');
    if (!confirmed) return;
    
    try {
        await deleteBrand(id);
        alert('Brand deleted!');
        loadBrandsAndProducts();
    } catch (error) {
        console.error('Error deleting brand:', error);
        alert('Error deleting brand!');
    }
}

// Add product
async function handleAddProduct() {
    const brands = await getAllBrands();
    
    if (brands.length === 0) {
        alert('Please create a brand first!');
        return;
    }
    
    // Show available brands
    let brandsList = 'Available brands:\n';
    brands.forEach(b => {
        brandsList += `${b.id}: ${b.name}\n`;
    });
    
    const brandId = prompt(brandsList + '\nEnter brand ID:');
    if (!brandId) return;
    
    const name = prompt('Product name:');
    if (!name) return;
    
    const sku = prompt('SKU:');
    if (!sku) return;
    
    const price = prompt('Price:');
    if (!price) return;
    
    const sportCategory = prompt('Sport category:');
    if (!sportCategory) return;
    
    const productType = prompt('Product type:');
    if (!productType) return;
    
    const stockQuantity = prompt('Stock quantity (optional):');
    
    try {
        await createProduct({
            brandId: parseInt(brandId),
            name: name,
            sku: sku,
            price: parseFloat(price),
            sportCategory: sportCategory,
            productType: productType,
            stockQuantity: stockQuantity ? parseInt(stockQuantity) : 0,
            isAvailable: true
        });
        
        alert('Product created!');
        loadBrandsAndProducts();
    } catch (error) {
        console.error('Error creating product:', error);
        alert('Error creating product!');
    }
}

// Edit product
async function handleEditProduct(id) {
    try {
        const product = await getProduct(id);
        
        const name = prompt('Product name:', product.name);
        if (!name) return;
        
        const sku = prompt('SKU:', product.sku);
        const price = prompt('Price:', product.price);
        const sportCategory = prompt('Sport category:', product.sportCategory);
        const productType = prompt('Product type:', product.productType);
        const stockQuantity = prompt('Stock quantity:', product.stockQuantity);
        
        await updateProduct(id, {
            name: name,
            sku: sku,
            price: parseFloat(price),
            sportCategory: sportCategory,
            productType: productType,
            stockQuantity: parseInt(stockQuantity),
            isAvailable: product.isAvailable
        });
        
        alert('Product updated!');
        loadBrandsAndProducts();
    } catch (error) {
        console.error('Error updating product:', error);
        alert('Error updating product!');
    }
}

// Delete product
async function handleDeleteProduct(id) {
    const confirmed = confirm('Delete this product?');
    if (!confirmed) return;
    
    try {
        await deleteProduct(id);
        alert('Product deleted!');
        loadBrandsAndProducts();
    } catch (error) {
        console.error('Error deleting product:', error);
        alert('Error deleting product!');
    }
}

addBrandBtn.addEventListener('click', handleAddBrand);
addProductBtn.addEventListener('click', handleAddProduct);

// Load data when page loads
loadBrandsAndProducts();

