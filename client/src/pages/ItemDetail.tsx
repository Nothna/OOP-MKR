import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { PostService } from '../API/post/post-service';
import { Post } from '../API/shared/Post';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button, Card} from "react-bootstrap";

const ItemDetail: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const [item, setItem] = useState<Post | null>(null);

    useEffect(() => {
        const fetchItem = async () => {
            try {
                const res = await PostService.getById(Number(id));
                setItem(res);
            } catch (error) {
                console.error('Error fetching item:', error);
            }
        };

        fetchItem();
    }, [id]);

    if (!item) {
        return <div>Loading...</div>;
    }

    return (

        <div className="d-flex col img-fluid m-5">
            <Card className="card-img" style={{width: "900px"}}>
                <h3>{item.title}</h3>

                <img
                    src={process.env.REACT_APP_SERVER_URL + '/static/image/' + item.image}
                    className="img-fluid"
                    alt={item.title}
                    style={{maxWidth: "800px"}}
                />
                <h3>{item.body}</h3>
            </Card>
            <Card className="align-items-center" style={{width: "900px"}}>
                <h3 style={{color: 'goldenrod'}}>Price: ${item.price}</h3>
                <p>Please, choose hours count</p>
                <input
                type={"text"}

                />
                <Button>Order</Button>
            </Card>




        </div>);

};

export default ItemDetail;
