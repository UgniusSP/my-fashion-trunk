import React, { useState } from 'react';
import usePost from '../hooks/usePost';
import {Table} from "./Table";

export const Main = () => {
    const [file, setFile] = useState<File | null>(null);
    const { loading, error, success, response, postData } = usePost('http://localhost:8080/images');

    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files) {
            setFile(event.target.files[0]);
        }
    };

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (file) {
            const formData = new FormData();
            formData.append('image', file);
            postData(formData);
        }
    };

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold mb-6 text-center">Upload Image</h1>
            <form onSubmit={handleSubmit} className="flex flex-col items-center mb-6">
                <input
                    type="file"
                    onChange={handleFileChange}
                    className="mb-4 p-2 border border-gray-300 rounded"
                />
                <button
                    type="submit"
                    disabled={loading}
                    className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 disabled:opacity-50"
                >
                    Upload
                </button>
            </form>
            {loading && <p className="text-yellow-500 text-center">Uploading...</p>}
            {error && <p className="text-red-500 text-center">Error: {error}</p>}
            {success && <p className="text-green-500 text-center">File uploaded successfully!</p>}
            <Table />
        </div>
    );
};