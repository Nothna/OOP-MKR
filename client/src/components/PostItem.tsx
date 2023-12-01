import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Card} from "react-bootstrap";

interface PostItemProps {
    title: string;
    price: string;
    photoUrl: string;
}

const PostItem: React.FC<PostItemProps> = ({ title, price, photoUrl }) => {
    console.log(photoUrl);
    return (
        <div className="card mb-3">
            <Card>
                <div>
                    <p className="card-text">Price: ${price}</p>
                </div>
                <Card>
                    <img src={process.env.REACT_APP_SERVER_URL + "/static/image/" + photoUrl} className="card-img-top" alt={title}
                         style={{width: "200px"}}
                    />
                    <div className="card-body">
                        <h5 className="card-title">{title}</h5>
                    </div>
                </Card>
            </Card>

        </div>
    );
};

export default PostItem;
