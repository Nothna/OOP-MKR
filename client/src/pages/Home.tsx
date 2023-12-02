/*
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
*/

import React, { useState, useEffect } from 'react';
import { PostService } from "../API/post/post-service";
import { Post } from "../API/shared/Post";
import PostItem from "../components/PostItem";

const Home: React.FC = () => {
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

    const chunkArray = (arr: Post[], size: number) => {
        return arr.reduce((chunks: Post[][], item: Post, index: number) => {
            const chunkIndex = index % size;

            if (!chunks[chunkIndex]) {
                chunks[chunkIndex] = [];
            }

            chunks[chunkIndex].push(item);
            return chunks;
        }, []);
    };

    const chunkedPosts = chunkArray(posts, 6);

    return (
        <div>
            <h2 style={{fontFamily: "monospace"}}>Availible Cars ~(˘▾˘~)</h2>
            <div className="row">
                {chunkedPosts.map((column: Post[], index: number) => (
                    <div key={index} className="col-lg-2 col-md-4 col-sm-6">
                        {column.map((post: Post) => (
                            <PostItem
                                key={post.id}
                                title={post.title}
                                price={post.price}
                                photoUrl={post.image}
                                id={post.id}
                            />
                        ))}
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Home;


