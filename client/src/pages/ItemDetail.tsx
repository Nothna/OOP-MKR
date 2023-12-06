import React, {FormEvent, useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import { PostService } from '../API/post/post-service';
import { Post } from '../API/shared/Post';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button, Card} from "react-bootstrap";
import {UserService} from "../API/user/user-service";

const ItemDetail: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const [item, setItem] = useState<Post | null>(null);
    const [hours, setHours] = useState(0);
    const [errMsg, setErrMsg] = useState("");
    const [resMsg, setResMsg] = useState("");

    const router = useNavigate();

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

    const order = async (event: FormEvent) =>{
        try{
            event.preventDefault();

            const accessToken = localStorage.getItem("accessToken");
            if(accessToken){
                const response = await UserService.order(Number(id), hours, accessToken);
                if (response.status === 200) setResMsg("Order success!");


            } else router("/home");
        } catch (e: any){
            setErrMsg("Error occurred");

        }

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
                type={"number"}

                />
                <Button onClick={order}>Order</Button>
                <label style={{color:"red"}}>{errMsg}</label>
                <label style={{color:"green"}}>{resMsg}</label>

            </Card>




        </div>);

};

export default ItemDetail;
