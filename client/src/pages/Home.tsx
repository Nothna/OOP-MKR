import React, { useState, useEffect } from 'react';
import { PostService } from "../API/post/post-service";
import { Post } from "../API/shared/Post";
import PostItem from "../components/PostItem";

const Home = () => {
    const [posts, setPosts] = useState<Post[]>([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await PostService.getAll();
                setPosts(res);
            } catch (error) {
                console.error('Error fetching posts:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <div>
            <h1>MAIN PAGE</h1>
            {posts.map(post => (
                <PostItem
                    key={post.id}
                    title={post.title}
                    price={post.price}
                    photoUrl={post.image}
                />
            ))}
        </div>
    );
};

export default Home;
