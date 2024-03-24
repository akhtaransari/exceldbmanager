import React, { useState, useEffect } from 'react';
import './Pagination.css'; // Import your CSS file
import DeleteButton from './DeleteButton';
import EditButton from './EditButton'

const Pagination = () => {
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    fetchData(currentPage);
  }, [currentPage]);

  const fetchData = async (page) => {
    try {
      const myHeaders = new Headers();
      myHeaders.append("Authorization", "Basic YWRtaW46YWRtaW4=");
  
      const requestOptions = {
        method: "GET",
        headers: myHeaders,
        redirect: "follow"
      };
  
      const response = await fetch(`http://app.afsar.tech/data/${page}`, requestOptions);
      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }
      const result = await response.json();
      setData(result.content);
      setTotalPages(result.totalPages);
    } catch (error) {
      console.error(error);
    }
  };
  

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  return (
    <div>
      <h2>Sales Data</h2>
      <table>
        <thead>
          <tr>
            <th>Market</th>
            <th>Country</th>
            <th>Product</th>
            <th>Discount Band</th>
            <th>Units Sold</th>
            <th>Manufacturing Price</th>
            <th>Sale Price</th>
            <th>Gross Sales</th>
            <th>Sales</th>
            <th>COGS</th>
            <th>Profit</th>
            <th>Date</th>
            <th>Month Number</th>
            <th>Month Name</th>
            <th>Year</th>
          </tr>
        </thead>
        <tbody>
          {data.map((row, index) => (
            <tr key={index}>
              <td>{row.market}</td>
              <td>{row.country}</td>
              <td>{row.product}</td>
              <td>{row.discountBand}</td>
              <td>{row.unitsSold}</td>
              <td>{row.manufacturingPrice}</td>
              <td>{row.salePrice}</td>
              <td>{row.grossSales}</td>
              <td>{row.sales}</td>
              <td>{row.cogs}</td>
              <td>{row.profit}</td>
              <td>{row.date}</td>
              <td>{row.monthNumber}</td>
              <td>{row.monthName}</td>
              <td>{row.year}</td>
              {DeleteButton(index)}
              {EditButton()}
            </tr>
          ))}
        </tbody>
      </table>
      <div className="pagination">
        {Array.from({ length: totalPages }, (_, index) => index + 1).map((page) => (
          <button
            key={page}
            className={currentPage === page ? 'active' : ''}
            onClick={() => handlePageChange(page)}
          >
            {page}
          </button>
        ))}
      </div>
    </div>
  );
};

export default Pagination;
