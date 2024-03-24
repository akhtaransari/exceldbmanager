import "./DeleteButton.css";

export default function DeleteButton({ id }) {
  const handleDelete = () => {
    const myHeaders = new Headers();
    myHeaders.append("Authorization", "Basic YWRtaW46YWRtaW4=");

    const requestOptions = {
      method: "DELETE",
      headers: myHeaders,
      redirect: "follow"
    };

    fetch(`http://app.afsar.tech/delete/${id}`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw new Error('Failed to delete item');
        }
        alert("Item deleted successfully");
      })
      .catch((error) => console.error(error));
  };

  return (
    <>
      <button onClick={handleDelete} className="beautiful-button">Delete</button>
    </>
  );
}
