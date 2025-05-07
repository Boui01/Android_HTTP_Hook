<?php

namespace App\Http\Controllers;

use App\Models\ProductModel;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;


class ProductController extends Controller
{
    public function index()
    {
        return DB::select('select * from products');
    }
}
