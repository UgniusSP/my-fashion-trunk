import { useState } from 'react';
import axios from 'axios';

const usePost = (endpoint: string) => {
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);
    const [success, setSuccess] = useState<boolean>(false);
    const [response, setResponse] = useState<any>(null);

    const postData = async (data: any) => {
        setLoading(true);
        setError(null);
        setSuccess(false);

        try {
            const res = await axios.post(endpoint, data);
            setResponse(res.data);
            setSuccess(true);
        } catch (err: any) {
            if(err.response.status === 403) {
                setError('The item is prohibited.');
            } else {
                setError('An error occurred while uploading the file.');
            }
        } finally {
            setLoading(false);
        }
    };

    return { loading, error, success, response, postData };
};

export default usePost;