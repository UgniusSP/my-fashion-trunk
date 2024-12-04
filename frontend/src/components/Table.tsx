export const Table = () => {
    return (
        <div className="mt-8">
            <h2 className="text-2xl font-bold mb-4 text-center">Allowed and Prohibited Items</h2>
            <table className="min-w-52 bg-white border border-gray-300 mx-auto text-center">
                <thead>
                <tr>
                    <th className="py-2 px-4 border-b text-center">Allowed</th>
                    <th className="py-2 px-4 border-b text-center">Prohibited</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td className="py-2 px-4 border-b text-center">Fashion accessories</td>
                    <td className="py-2 px-4 border-b text-center">Food products</td>
                </tr>
                <tr>
                    <td className="py-2 px-4 border-b text-center">All types of clothes</td>
                    <td className="py-2 px-4 border-b text-center">Sports equipment</td>
                </tr>
                <tr>
                    <td className="py-2 px-4 border-b text-center">All kinds of footwear</td>
                    <td className="py-2 px-4 border-b text-center">Tobacco products</td>
                </tr>
                <tr>
                    <td className="py-2 px-4 border-b text-center">Cosmetics</td>
                    <td className="py-2 px-4 border-b text-center">Cleaning supplies</td>
                </tr>
                <tr>
                    <td className="py-2 px-4 border-b text-center">Children toys</td>
                    <td className="py-2 px-4 border-b text-center">Weapons and armory</td>
                </tr>
                <tr>
                    <td className="py-2 px-4 border-b text-center">Tech accessories</td>
                    <td className="py-2 px-4 border-b text-center">Vehicles and automotive parts</td>
                </tr>
                <tr>
                    <td className="py-2 px-4 border-b text-center">Pet care products</td>
                    <td className="py-2 px-4 border-b text-center">Natural fur products</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}