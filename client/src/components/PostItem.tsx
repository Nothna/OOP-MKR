import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Card} from "react-bootstrap";
import {NavLink} from "react-router-dom";

interface PostItemProps {
    title: string;
    price: string;
    photoUrl: string;
    id: number;
}

const PostItem: React.FC<PostItemProps> = ({ title, price, photoUrl,id }) => {
    console.log(photoUrl);
    return (
        <div className="card mb-3" style={{maxWidth: "250px"}}>
            <Card>
                <div>
                    <p className="card-text">Price: ${price}</p>
                </div>
                <Card>
                    <div className="card-body">
                        <NavLink to={`/item/${id}`} className="card-title">{title}</NavLink>
                    </div>
                    <img src={process.env.REACT_APP_SERVER_URL + "/static/image/" + photoUrl} className="card-img-top"
                         style={{width: "200px"}}
                         alt={title}
                    />

                </Card>
            </Card>

        </div>
    );
};

export default PostItem;
