import React, { useState, useEffect } from 'react';
import './App.css';

const API_BASE_URL = 'http://localhost:8080/api';

function App() {
  const [brands, setBrands] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadBrandsAndProducts();
  }, []);

  async function loadBrandsAndProducts() {
    setLoading(true);
    try {
      const response = await fetch(`${API_BASE_URL}/brands`);
      const brandsData = await response.json();

      for (const brand of brandsData) {
        const productsResponse = await fetch(`${API_BASE_URL}/products/brand/${brand.id}`);
        brand.products = await productsResponse.json();
      }

      setBrands(brandsData);
    } catch (error) {
      console.error('Error:', error);
      alert('Error loading data. Make sure your API is running!');
    } finally {
      setLoading(false);
    }
  }

  async function handleAddBrand() {
    const name = prompt('Brand name:');
    if (!name) return;

    const year = prompt('Founding year (optional):');
    const website = prompt('Website (optional):');
    const description = prompt('Description (optional):');

    try {
      await fetch(`${API_BASE_URL}/brands`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          name: name,
          foundingYear: year ? parseInt(year) : null,
          website: website || null,
          description: description || null,
          isActive: true
        })
      });
      alert('Brand created!');
      loadBrandsAndProducts();
    } catch (error) {
      console.error('Error:', error);
      alert('Error creating brand!');
    }
  }

  async function handleEditBrand(brand) {
    const name = prompt('Brand name:', brand.name);
    if (!name) return;

    const year = prompt('Founding year:', brand.foundingYear);
    const website = prompt('Website:', brand.website);
    const description = prompt('Description:', brand.description);

    try {
      await fetch(`${API_BASE_URL}/brands/${brand.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          name: name,
          foundingYear: year ? parseInt(year) : null,
          website: website || null,
          description: description || null,
          isActive: brand.isActive
        })
      });
      alert('Brand updated!');
      loadBrandsAndProducts();
    } catch (error) {
      console.error('Error:', error);
      alert('Error updating brand!');
    }
  }

  async function handleDeleteBrand(id) {
    if (!window.confirm('Delete this brand? This will also delete all its products!')) return;

    try {
      await fetch(`${API_BASE_URL}/brands/${id}`, {
        method: 'DELETE'
      });
      alert('Brand deleted!');
      loadBrandsAndProducts();
    } catch (error) {
      console.error('Error:', error);
      alert('Error deleting brand!');
    }
  }

  async function handleAddProduct() {
    if (brands.length === 0) {
      alert('Please create a brand first!');
      return;
    }

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
      await fetch(`${API_BASE_URL}/products`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          brandId: parseInt(brandId),
          name: name,
          sku: sku,
          price: parseFloat(price),
          sportCategory: sportCategory,
          productType: productType,
          stockQuantity: stockQuantity ? parseInt(stockQuantity) : 0,
          isAvailable: true
        })
      });
      alert('Product created!');
      loadBrandsAndProducts();
    } catch (error) {
      console.error('Error:', error);
      alert('Error creating product!');
    }
  }

  async function handleEditProduct(product) {
    const name = prompt('Product name:', product.name);
    if (!name) return;

    const sku = prompt('SKU:', product.sku);
    const price = prompt('Price:', product.price);
    const sportCategory = prompt('Sport category:', product.sportCategory);
    const productType = prompt('Product type:', product.productType);
    const stockQuantity = prompt('Stock quantity:', product.stockQuantity);

    try {
      await fetch(`${API_BASE_URL}/products/${product.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          name: name,
          sku: sku,
          price: parseFloat(price),
          sportCategory: sportCategory,
          productType: productType,
          stockQuantity: parseInt(stockQuantity),
          isAvailable: product.isAvailable
        })
      });
      alert('Product updated!');
      loadBrandsAndProducts();
    } catch (error) {
      console.error('Error:', error);
      alert('Error updating product!');
    }
  }

  async function handleDeleteProduct(id) {
    if (!window.confirm('Delete this product?')) return;

    try {
      await fetch(`${API_BASE_URL}/products/${id}`, {
        method: 'DELETE'
      });
      alert('Product deleted!');
      loadBrandsAndProducts();
    } catch (error) {
      console.error('Error:', error);
      alert('Error deleting product!');
    }
  }

  if (loading) {
    return (
      <div className="loading">
        <div className="spinner"></div>
        <p>Loading...</p>
      </div>
    );
  }

  return (
    <div className="App">
      <header className="header">
        <h1>Sports Equipment Manager</h1>
        <p className="subtitle">Built with React (Create React App)</p>
      </header>

      <main className="container">
        <h2>Brands & Products</h2>
        <button onClick={handleAddBrand}>+ Add Brand</button>
        <button onClick={handleAddProduct}>+ Add Product</button>

        <div id="brands-container">
          {brands.length === 0 ? (
            <p>No brands yet. Add one!</p>
          ) : (
            brands.map(brand => (
              <BrandSection
                key={brand.id}
                brand={brand}
                onEditBrand={handleEditBrand}
                onDeleteBrand={handleDeleteBrand}
                onEditProduct={handleEditProduct}
                onDeleteProduct={handleDeleteProduct}
              />
            ))
          )}
        </div>
      </main>
    </div>
  );
}

function BrandSection({ brand, onEditBrand, onDeleteBrand, onEditProduct, onDeleteProduct }) {
  return (
    <div className="brand-section">
      <div className="brand-header">
        <h3>{brand.name}</h3>
        <p>Founded: {brand.foundingYear || 'N/A'}</p>
        <p>Website: {brand.website || 'N/A'}</p>
        <p>{brand.description || ''}</p>
        <button onClick={() => onEditBrand(brand)}>Edit</button>
        <button onClick={() => onDeleteBrand(brand.id)}>Delete</button>
      </div>

      <div className="products-section">
        <h4>Products:</h4>
        {brand.products.length === 0 ? (
          <p>No products yet.</p>
        ) : (
          brand.products.map(product => (
            <ProductCard
              key={product.id}
              product={product}
              onEdit={onEditProduct}
              onDelete={onDeleteProduct}
            />
          ))
        )}
      </div>
    </div>
  );
}

function ProductCard({ product, onEdit, onDelete }) {
  return (
    <div className="product-card">
      <h5>{product.name}</h5>
      <p>SKU: {product.sku}</p>
      <p>Price: ${product.price}</p>
      <p>Category: {product.sportCategory}</p>
      <p>Type: {product.productType}</p>
      <p>Stock: {product.stockQuantity}</p>
      <button onClick={() => onEdit(product)}>Edit</button>
      <button onClick={() => onDelete(product.id)}>Delete</button>
    </div>
  );
}

export default App;
